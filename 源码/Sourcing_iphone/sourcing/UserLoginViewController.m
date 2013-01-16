//
//  UserLoginViewController.m
//  sourcing
//
//  Created by wuzhu on 13-1-15.
//  Copyright (c) 2013年 wuzhu. All rights reserved.
//

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Imports

#import "UserLoginViewController.h"
#import "ApplicationSet.h"
#import "UserVO.h"
#import "StringUtil.h"
#import "MobileUserService.h"
#import "DataInterfaceUtil.h"
#import "ToastHintUtil.h"
#import "ResponseVO.h"
#import "JsonParser.h"

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Types

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Defines & Constants

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Macros

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Private Interface

@interface UserLoginViewController ()

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Private Properties

@end


////////////////////////////////////////////////////////////////////////////////
#pragma mark - Implementation

@implementation UserLoginViewController

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Synthesize

/* Outlets ********************************************************************/

/* Public *********************************************************************/

/* Private ********************************************************************/

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Setup & Teardown

- (void)commonInitUserLoginViewController
{
}

- (id)initWithNibName:(NSString*)nibNameOrNil bundle:(NSBundle*)nibBundleOrNil
{
    self = [super initWithNibName:nibNameOrNil bundle:nibBundleOrNil];
    if (self)
    {
        [self commonInitUserLoginViewController];
    }
    return self;
}

- (id)initWithCoder:(NSCoder*)aDecoder
{
    self = [super initWithCoder:aDecoder];
    if (self)
    {
        [self commonInitUserLoginViewController];
    }
    return self;
}

- (void)viewDidLoad {
    [super viewDidLoad];
    // your code here
}

-(void)viewWillAppear:(BOOL)animated
{
    [super viewWillAppear:animated];
    if ([StringUtil isEmpty:self.textFieldUserName.text]) {
        [self.textFieldUserName becomeFirstResponder];
    }else
    {
        [self.textFieldPwd becomeFirstResponder];
    }
}

- (void)viewDidUnload {
	// your code here
    [self setLabelTitle:nil];
    [self setViewInput:nil];
    [self setTextFieldUserName:nil];
    [self setTextFieldPwd:nil];
    [super viewDidUnload];
}

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Superclass Overrides

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Public methods

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Private methods

-(void)userLogin
{
    //用户登录
    NSString *account = [self.textFieldUserName text];
    if([StringUtil isEmpty:account])
    {
        [ToastHintUtil showHint:NSLocalizedString(@"请输入您的用户名", @"") parentview:self.view];
        return;
    }
    NSString *pwd = [self.textFieldPwd text];
    if([StringUtil isEmpty:pwd])
    {
        [ToastHintUtil showHint:NSLocalizedString(@"请输入您的密码", @"") parentview:self.view];
        return;
    }
    MobileUserService *service = [[MobileUserService alloc] initWithDelegate:self tag:CINT_TAG_USERLOGIN];
    [service userLogin:account password:pwd process:YES processcontent:NSLocalizedString(@"用户登录中...",@"")];
}

-(void)userRegister
{
    //用户注册
    [[UIApplication sharedApplication] openURL:[NSURL URLWithString:[DataInterfaceUtil getDataInterface:@"url_reg_progress"]]];
}

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Actions
- (IBAction)actionReturn:(id)sender {
    [self dismissModalViewControllerAnimated:YES];
}

- (IBAction)actionRegister:(id)sender {
    [self userRegister];
}

- (IBAction)actionLogin:(id)sender {
    [self.textFieldPwd resignFirstResponder];
    [self userLogin];
}
////////////////////////////////////////////////////////////////////////////////
#pragma mark - Delegate methods
-(BOOL)textFieldShouldReturn:(UITextField *)textField
{
    BOOL result = NO;//返回值为NO，即 忽略 按下此键；若返回为YES则 认为 用户按下了此键，并去调用TextFieldDoneEditint方法
    if(textField == self.textFieldUserName)
    {
        [self.textFieldPwd becomeFirstResponder];
    }
    else
    {
        [textField resignFirstResponder];
        [self userLogin];
    }
    return result;
}

-(void)textFieldDidEndEditing:(UITextField *)textField
{
}

-(void)serviceResult:(ResponseVO *)result
{
    if(result==nil||result.tag != CINT_TAG_USERLOGIN)
        return;
    if(![result isSucess])
    {
        [ToastHintUtil showHint:[NSString stringWithFormat:@"登录失败:%@",result.msg] parentview:self.view];
        return;
    }
    ResponseVO *rvo = [[ResponseVO alloc] init];
    UserVO *vo = (UserVO *)[JsonParser parseJsonToEntity:result.msg respVO:&rvo ref:nil];
    if (vo==nil||![rvo isSucess]) {
        [ToastHintUtil showHint:[NSString stringWithFormat:@"登录失败:%@",rvo.msg] parentview:self.view];
        return;
    }
    [[ApplicationSet shareData] setLoginUserInfo:vo saveinfo:YES];
    //[ToastHintUtil showHint:NSLocalizedString(@"登录成功", @"") parentview:self.view];
    [self dismissModalViewControllerAnimated:YES];
}

@end

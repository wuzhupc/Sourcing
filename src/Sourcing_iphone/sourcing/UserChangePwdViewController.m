//
//  UserChangePwdViewController.m
//  sourcing
//
//  Created by wuzhu on 13-1-15.
//  Copyright (c) 2013年 wuzhu. All rights reserved.
//

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Imports

#import "UserChangePwdViewController.h"
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

@interface UserChangePwdViewController ()

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Private Properties

@end


////////////////////////////////////////////////////////////////////////////////
#pragma mark - Implementation

@implementation UserChangePwdViewController

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Synthesize

/* Outlets ********************************************************************/

/* Public *********************************************************************/

/* Private ********************************************************************/

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Setup & Teardown

- (void)commonInitUserChangePwdViewController
{
}

- (id)initWithNibName:(NSString*)nibNameOrNil bundle:(NSBundle*)nibBundleOrNil
{
    self = [super initWithNibName:nibNameOrNil bundle:nibBundleOrNil];
    if (self)
    {
        [self commonInitUserChangePwdViewController];
    }
    return self;
}

- (id)initWithCoder:(NSCoder*)aDecoder
{
    self = [super initWithCoder:aDecoder];
    if (self)
    {
        [self commonInitUserChangePwdViewController];
    }
    return self;
}

- (void)viewDidLoad {
    [super viewDidLoad];
    // your code here
    [self.viewInput setCellCount:3];
}

- (void)viewDidUnload {
	// your code here
    [self setViewInput:nil];
    [self setTextFieldOldPwd:nil];
    [self setTextFieldNewPwd:nil];
    [self setTextFieldConfirmNewPwd:nil];
    [super viewDidUnload];
}

-(void)viewWillAppear:(BOOL)animated
{
    [super viewWillAppear:animated];
    [self.textFieldOldPwd becomeFirstResponder];
}

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Superclass Overrides

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Public methods

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Private methods

-(void)changePwd
{
    NSString *oldpwd = self.textFieldOldPwd.text;
    if([StringUtil isEmpty:oldpwd])
    {
        [ToastHintUtil showHint:NSLocalizedString(@"请输入旧密码", @"") parentview:self.view];
        return;
    }
    UserVO *vo = [[ApplicationSet shareData] getUserVO];

    if(![vo.password isEqualToString:oldpwd])
    {
        [self.textFieldOldPwd setText:@""];
        [ToastHintUtil showHint:NSLocalizedString(@"请输入正确旧密码", @"") parentview:self.view];
        return;
    }
    
    NSString *newpwd = self.textFieldNewPwd.text;
    if([StringUtil isEmpty:newpwd])
    {
        [ToastHintUtil showHint:NSLocalizedString(@"请输入新密码", @"") parentview:self.view];
        return;
    }
    if([newpwd isEqualToString:oldpwd])
    {
        [self.textFieldNewPwd setText:@""];
        [self.textFieldConfirmNewPwd setText:@""];
        [ToastHintUtil showHint:NSLocalizedString(@"新密码不能和旧密码一样", @"") parentview:self.view];
        return;
    }
    NSString *confirmNewPwd = self.textFieldConfirmNewPwd.text;
    if(![newpwd isEqualToString:confirmNewPwd])
    {
        [self.textFieldConfirmNewPwd setText:@""];
        [ToastHintUtil showHint:NSLocalizedString(@"新密码不一致", @"") parentview:self.view];
        return;
    }
    MobileUserService *service = [[MobileUserService alloc] initWithDelegate:self tag:CINT_TAG_USERCHANGPWD];
    [service changePwd:vo.useraccount password:oldpwd newpwd:newpwd process:YES processcontent:NSLocalizedString(@"修改密码中...", @"")];
}

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Actions
- (IBAction)actionReturn:(id)sender
{
    [self dismissModalViewControllerAnimated:YES];
}

- (IBAction)actonChangePwd:(id)sender {
    [self.textFieldOldPwd resignFirstResponder];
    [self.textFieldNewPwd resignFirstResponder];
    [self.textFieldConfirmNewPwd resignFirstResponder];
    [self changePwd];
}

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Delegate methods

-(BOOL)textFieldShouldReturn:(UITextField *)textField
{
    BOOL result = NO;
    if(textField == self.textFieldOldPwd)
    {
        [self.textFieldNewPwd becomeFirstResponder];
    }else if(textField == self.textFieldNewPwd)
    {
        [self.textFieldConfirmNewPwd becomeFirstResponder];
    }else
    {
        [self.textFieldConfirmNewPwd resignFirstResponder];
        [self changePwd];
    }
    
    return result;
}

-(void)textFieldDidEndEditing:(UITextField *)textField
{
}

-(void)serviceResult:(ResponseVO *)result
{
    if(result==nil||result.tag != CINT_TAG_USERCHANGPWD)
        return;
    if(![result isSucess])
    {
        [ToastHintUtil showHint:[NSString stringWithFormat:@"修改密码失败：%@",result.msg] parentview:self.view];
        return;
    }
    ResponseVO *rvo = [JsonParser parseJsonToResponse:result.msg];
    if(![rvo isSucess])
    {
        [ToastHintUtil showHint:[NSString stringWithFormat:@"修改密码失败：%@",rvo.msg] parentview:self.view];
        return;
    }
    UserVO *vo = [[ApplicationSet shareData] getUserVO];
    vo.password = self.textFieldNewPwd.text;
    [[ApplicationSet shareData] setLoginUserInfo:vo saveinfo:YES];
    //[ToastHintUtil showHint:@"修改密码成功" parentview:self.view];
    [self dismissModalViewControllerAnimated:YES];
}

@end

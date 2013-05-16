//
//  NewConsultViewController.m
//  sourcing
//
//  Created by wuzhu on 13-1-20.
//  Copyright (c) 2013年 wuzhu. All rights reserved.
//

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Imports

#import "NewConsultViewController.h"
#import "ApplicationSet.h"
#import "UserVO.h"
#import "StringUtil.h"
#import "MobileUserService.h"
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

@interface NewConsultViewController ()

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Private Properties

@end


////////////////////////////////////////////////////////////////////////////////
#pragma mark - Implementation

@implementation NewConsultViewController

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Synthesize

/* Outlets ********************************************************************/

/* Public *********************************************************************/

/* Private ********************************************************************/

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Setup & Teardown

- (void)commonInitNewConsultViewController
{
}

- (id)initWithNibName:(NSString*)nibNameOrNil bundle:(NSBundle*)nibBundleOrNil
{
    self = [super initWithNibName:nibNameOrNil bundle:nibBundleOrNil];
    if (self)
    {
        [self commonInitNewConsultViewController];
    }
    return self;
}

- (id)initWithCoder:(NSCoder*)aDecoder
{
    self = [super initWithCoder:aDecoder];
    if (self)
    {
        [self commonInitNewConsultViewController];
    }
    return self;
}

- (void)viewDidLoad {
    [super viewDidLoad];
    // your code here
    [self.viewConsult setCellCount:1];
    [self.textViewConsult setPlaceholder:@"请输入咨询信息，我们将尽快给予答复"];
}

-(void)viewWillAppear:(BOOL)animated
{
    [super viewWillAppear:YES];
    [self.textViewConsult becomeFirstResponder];
}

- (void)viewDidUnload {
	// your code here
    [self setViewConsult:nil];
    [self setTextViewConsult:nil];
    [super viewDidUnload];
}

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Superclass Overrides

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Public methods

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Private methods

-(void)sendConsult
{
    NSString *consult = self.textViewConsult.text;
    if([StringUtil isEmpty:consult])
    {
        [ToastHintUtil showHint:NSLocalizedString(@"请输入咨询信息", @"")];
        return;
    }
    MobileUserService *service = [[MobileUserService alloc] initWithDelegate:self tag:CINT_TAG_SENDCONSULT];
    [service sendUserConsult:consult process:YES processcontent:NSLocalizedString(@"咨询信息提交中...", @"")];
}

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Actions
- (IBAction)actionReturn:(id)sender
{
    [self dismissModalViewControllerAnimated:YES];
}

- (IBAction)actionSendConsult:(id)sender {
    [self.textViewConsult resignFirstResponder];
    [self sendConsult];
}
////////////////////////////////////////////////////////////////////////////////
#pragma mark - Delegate methods


-(void)serviceResult:(ResponseVO *)result
{
    if(result==nil||result.tag != CINT_TAG_SENDCONSULT)
        return;
    if(![result isSucess])
    {
        [ToastHintUtil showHint:[NSString stringWithFormat:@"提交咨询信息失败:：%@",result.msg] parentview:self.view];
        return;
    }
    ResponseVO *rvo = [JsonParser parseJsonToResponse:result.msg];
    if(![rvo isSucess])
    {
        [ToastHintUtil showHint:[NSString stringWithFormat:@"提交咨询信息失败:：%@",rvo.msg] parentview:self.view];
        return;
    }
    UserVO *vo = [[ApplicationSet shareData] getUserVO];
    vo.allconsultcount = vo.allconsultcount +1;
    [[ApplicationSet shareData] setLoginUserInfo:vo saveinfo:YES];
    [self dismissModalViewControllerAnimated:YES];
}
@end

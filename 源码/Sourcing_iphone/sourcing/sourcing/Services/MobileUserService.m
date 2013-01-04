//
//  MobileUserService.m
//  sourcing
//
//  Created by wuzhu on 12-12-31.
//  Copyright (c) 2012年 wuzhu. All rights reserved.
//

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Imports

#import "MobileUserService.h"
#import "DataInterfaceUtil.h"
#import "JsonCreater.h"
#import "UserVO.h"

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Types

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Defines & Constants

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Macros

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Private Interface
@interface MobileUserService ()

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Private Properties

@end

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Implementation

@implementation MobileUserService

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Synthesize

/* Public *********************************************************************/

/* Private ********************************************************************/

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Setup & Teardown


////////////////////////////////////////////////////////////////////////////////
#pragma mark - Superclass Overrides

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Public methods
-(void)userLogin:(NSString *)kusername password:(NSString *)kpwd
{
    [self userLogin:kusername password:kpwd process:NO processcontent:nil];
}
-(void)userLogin:(NSString *)kusername password:(NSString *)kpwd  process:(BOOL)kshowprocess processcontent:(NSString *)kshowprocesscontent
{
    _commandName = [DataInterfaceUtil getDataInterface:@"cmd_json_user_login"];
    [self setAssetsFileInfo:_commandName suffix:kusername];
    JsonCreater *creater = [[JsonCreater alloc] init];
    [creater setParam:@"devid" paramValue:[BaseJsonService getDevID]];
    [creater setParam:@"username" paramValue:kusername];
    [creater setParam:@"password" paramValue:kpwd];
    [self getData:[creater createJson:_commandName] url:nil process:kshowprocess processcontent:kshowprocesscontent];
}

-(void)changePwd:(NSString *)kusername password:(NSString *)kpwd newpwd:(NSString *)knewpwd process:(BOOL)kshowprocess processcontent:(NSString *)kshowprocesscontent
{
    _commandName = [DataInterfaceUtil getDataInterface:@"cmd_json_user_change_pwd"];
    [self setAssetsFileInfo:_commandName suffix:kusername];
    JsonCreater *creater = [[JsonCreater alloc] init];
    [creater setParam:@"devid" paramValue:[BaseJsonService getDevID]];
    [creater setParam:@"username" paramValue:kusername];
    [creater setParam:@"password" paramValue:kpwd];
    [creater setParam:@"newpassword" paramValue:knewpwd];
    [self getData:[creater createJson:_commandName] url:nil process:kshowprocess processcontent:kshowprocesscontent];
}
-(void)getUserConsultInfo:(NSString *)kbottomid process:(BOOL)kshowprocess processcontent:(NSString *)kshowprocesscontent
{
    UserVO *uservo = [BaseJsonService getUserInfo];
    if(uservo==nil)
    {
        [self sendServiceFailInfo:NSLocalizedString(@"No user login.", @"提示用户未登录")];
        return;
    }
    _commandName = [DataInterfaceUtil getDataInterface:@"cmd_json_user_consult_info"];
    [self setAssetsFileInfo:_commandName suffix:nil];
    JsonCreater *creater = [[JsonCreater alloc] init];
    [creater setParam:@"devid" paramValue:[BaseJsonService getDevID]];
    [creater setParam:@"userid" paramValue:uservo.Userid];
    [creater setParam:@"pagesize" paramValue:CINT_PAGE_SIZE];
    [creater setParam:@"bottomid" paramValue:kbottomid];
    [self getData:[creater createJson:_commandName] url:nil process:kshowprocess processcontent:kshowprocesscontent];
}
-(void)sendUserConsult:(NSString *)kcontent process:(BOOL)kshowprocess processcontent:(NSString *)kshowprocesscontent
{
    UserVO *uservo = [BaseJsonService getUserInfo];
    if(uservo==nil)
    {
        [self sendServiceFailInfo:NSLocalizedString(@"No user login.", @"提示用户未登录")];
        return;
    }
    _commandName = [DataInterfaceUtil getDataInterface:@"cmd_json_send_user_consult"];
    [self setAssetsFileInfo:_commandName suffix:nil];
    JsonCreater *creater = [[JsonCreater alloc] init];
    [creater setParam:@"devid" paramValue:[BaseJsonService getDevID]];
    [creater setParam:@"userid" paramValue:uservo.Userid];
    [creater setParam:@"username" paramValue:uservo.username];
    [creater setParam:@"conetnt" paramValue:kcontent];
    [self getData:[creater createJson:_commandName] url:nil process:kshowprocess processcontent:kshowprocesscontent];
    
}
-(void)getUserAuditInfo:(NSString *)kbottomid process:(BOOL)kshowprocess processcontent:(NSString *)kshowprocesscontent
{
    UserVO *uservo = [BaseJsonService getUserInfo];
    if(uservo==nil)
    {
        [self sendServiceFailInfo:NSLocalizedString(@"No user login.", @"提示用户未登录")];
        return;
    }
    _commandName = [DataInterfaceUtil getDataInterface:@"cmd_json_get_user_audit_info"];
    [self setAssetsFileInfo:_commandName suffix:nil];
    JsonCreater *creater = [[JsonCreater alloc] init];
    [creater setParam:@"devid" paramValue:[BaseJsonService getDevID]];
    [creater setParam:@"userid" paramValue:uservo.Userid];
    [creater setParam:@"pagesize" paramValue:CINT_PAGE_SIZE];
    [creater setParam:@"bottomid" paramValue:kbottomid];
    [self getData:[creater createJson:_commandName] url:nil process:kshowprocess processcontent:kshowprocesscontent];
}
-(void)getUserDeclareInfo:(NSString *)kbottomid process:(BOOL)kshowprocess processcontent:(NSString *)kshowprocesscontent
{
    UserVO *uservo = [BaseJsonService getUserInfo];
    if(uservo==nil)
    {
        [self sendServiceFailInfo:NSLocalizedString(@"No user login.", @"提示用户未登录")];
        return;
    }
    _commandName = [DataInterfaceUtil getDataInterface:@"cmd_json_get_user_declare_info"];
    [self setAssetsFileInfo:_commandName suffix:nil];
    JsonCreater *creater = [[JsonCreater alloc] init];
    [creater setParam:@"devid" paramValue:[BaseJsonService getDevID]];
    [creater setParam:@"userid" paramValue:uservo.Userid];
    [creater setParam:@"pagesize" paramValue:CINT_PAGE_SIZE];
    [creater setParam:@"bottomid" paramValue:kbottomid];
    [self getData:[creater createJson:_commandName] url:nil process:kshowprocess processcontent:kshowprocesscontent];
    
}
-(void)getUserNotifierInfo:(NSString *)kbottomid process:(BOOL)kshowprocess processcontent:(NSString *)kshowprocesscontent
{
    UserVO *uservo = [BaseJsonService getUserInfo];
    if(uservo==nil)
    {
        [self sendServiceFailInfo:NSLocalizedString(@"No user login.", @"提示用户未登录")];
        return;
    }
    _commandName = [DataInterfaceUtil getDataInterface:@"cmd_json_get_user_notifier_info"];
    [self setAssetsFileInfo:_commandName suffix:nil];
    JsonCreater *creater = [[JsonCreater alloc] init];
    [creater setParam:@"devid" paramValue:[BaseJsonService getDevID]];
    [creater setParam:@"userid" paramValue:uservo.Userid];
    [creater setParam:@"pagesize" paramValue:CINT_PAGE_SIZE];
    [creater setParam:@"bottomid" paramValue:kbottomid];
    [self getData:[creater createJson:_commandName] url:nil process:kshowprocess processcontent:kshowprocesscontent];
    
}

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Private methods

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Actions

////////////////////////////////////////////////////////////////////////////////
#pragma mark - XXXDataSource / XXXDelegate methods


@end

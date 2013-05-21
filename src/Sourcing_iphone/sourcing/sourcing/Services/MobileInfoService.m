//
//  MobileInfoService.m
//  sourcing
//
//  Created by wuzhu on 13-1-1.
//  Copyright (c) 2013年 wuzhu. All rights reserved.
//

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Imports

#import "MobileInfoService.h"
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
@interface MobileInfoService ()

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Private Properties

@end

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Implementation

@implementation MobileInfoService

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
-(void)getNewsList:(NSString *)knewstype bottomid:(NSString *)kid
{
    _commandName = [DataInterfaceUtil getDataInterface:@"cmd_json_get_news_list"];
    [self setAssetsFileInfo:_commandName suffix:knewstype];
    JsonCreater *creater = [[JsonCreater alloc] init];
    [creater setParam:@"devid" paramValue:[BaseJsonService getDevID]];
    [creater setParam:@"newstype" paramValue:knewstype];
    [creater setParam:@"pagesize" paramValue:CNUM_PAGE_SIZE];
    [creater setParam:@"bottomnewsid" paramValue:kid];
    [self getData:[creater createJson:_commandName] url:nil];
}
-(void)getNewsDetail:(NSString *)knewstype newsid:(NSString *)kid
{
    _commandName = [DataInterfaceUtil getDataInterface:@"cmd_json_get_news_detail"];
    [self setAssetsFileInfo:_commandName suffix:[NSString stringWithFormat:@"%@_%@",knewstype,kid]];
    JsonCreater *creater = [[JsonCreater alloc] init];
    [creater setParam:@"devid" paramValue:[BaseJsonService getDevID]];
    [creater setParam:@"newstype" paramValue:knewstype];
    [creater setParam:@"newsid" paramValue:kid];
    [self getData:[creater createJson:_commandName] url:nil];
    
}
-(void)getJobList:(NSString *)ksearchkey bottomid:(NSString *)kid
{
    _commandName = [DataInterfaceUtil getDataInterface:@"cmd_json_get_job_list"];
    [self setAssetsFileInfo:_commandName suffix:nil];
    JsonCreater *creater = [[JsonCreater alloc] init];
    [creater setParam:@"devid" paramValue:[BaseJsonService getDevID]];
    [creater setParam:@"searchkey" paramValue:ksearchkey];
    [creater setParam:@"pagesize" paramValue:CNUM_PAGE_SIZE];
    [creater setParam:@"bottomid" paramValue:kid];
    [self getData:[creater createJson:_commandName] url:nil];
    
}
-(void)getResumeList:(NSString *)ksearchkey bottomid:(NSString *)kid
{
    _commandName = [DataInterfaceUtil getDataInterface:@"cmd_json_get_resume_list"];
    [self setAssetsFileInfo:_commandName suffix:nil];
    JsonCreater *creater = [[JsonCreater alloc] init];
    [creater setParam:@"devid" paramValue:[BaseJsonService getDevID]];
    [creater setParam:@"searchkey" paramValue:ksearchkey];
    [creater setParam:@"pagesize" paramValue:CNUM_PAGE_SIZE];
    [creater setParam:@"bottomid" paramValue:kid];
    [self getData:[creater createJson:_commandName] url:nil];
    
}
-(void)getTrainList:(NSString *)ksearchkey bottomid:(NSString *)kid
{
    _commandName = [DataInterfaceUtil getDataInterface:@"cmd_json_get_train_list"];
    [self setAssetsFileInfo:_commandName suffix:nil];
    JsonCreater *creater = [[JsonCreater alloc] init];
    [creater setParam:@"devid" paramValue:[BaseJsonService getDevID]];
    [creater setParam:@"searchkey" paramValue:ksearchkey];
    [creater setParam:@"pagesize" paramValue:CNUM_PAGE_SIZE];
    [creater setParam:@"bottomid" paramValue:kid];
    [self getData:[creater createJson:_commandName] url:nil];
    
}
-(void)getProjectList:(NSString *)ksearchkey bottomid:(NSString *)kid
{
    _commandName = [DataInterfaceUtil getDataInterface:@"cmd_json_get_project_list"];
    [self setAssetsFileInfo:_commandName suffix:nil];
    JsonCreater *creater = [[JsonCreater alloc] init];
    [creater setParam:@"devid" paramValue:[BaseJsonService getDevID]];
    [creater setParam:@"searchkey" paramValue:ksearchkey];
    [creater setParam:@"pagesize" paramValue:CNUM_PAGE_SIZE];
    [creater setParam:@"bottomid" paramValue:kid];
    [self getData:[creater createJson:_commandName] url:nil];
    
}
-(void)getCompanyList:(NSString *)ksearchkey bottomid:(NSString *)kid
{
    _commandName = [DataInterfaceUtil getDataInterface:@"cmd_json_get_company_list"];
    [self setAssetsFileInfo:_commandName suffix:nil];
    JsonCreater *creater = [[JsonCreater alloc] init];
    [creater setParam:@"devid" paramValue:[BaseJsonService getDevID]];
    [creater setParam:@"searchkey" paramValue:ksearchkey];
    [creater setParam:@"pagesize" paramValue:CNUM_PAGE_SIZE];
    [creater setParam:@"bottomid" paramValue:kid];
    [self getData:[creater createJson:_commandName] url:nil];
    
}
-(void)getJobDetail:(NSString *)kid
{
    _commandName = [DataInterfaceUtil getDataInterface:@"cmd_json_get_job_detail"];
    [self setAssetsFileInfo:_commandName suffix:nil];
    JsonCreater *creater = [[JsonCreater alloc] init];
    [creater setParam:@"devid" paramValue:[BaseJsonService getDevID]];
    [creater setParam:@"jobid" paramValue:kid];
    [self getData:[creater createJson:_commandName] url:nil];
    
}
-(void)getCompanyDetail:(NSString *)kid
{
    _commandName = [DataInterfaceUtil getDataInterface:@"cmd_json_get_company_detail"];
    [self setAssetsFileInfo:_commandName suffix:nil];
    JsonCreater *creater = [[JsonCreater alloc] init];
    [creater setParam:@"devid" paramValue:[BaseJsonService getDevID]];
    [creater setParam:@"companyid" paramValue:kid];
    [self getData:[creater createJson:_commandName] url:nil];
    
}
-(void)getResumeDetail:(NSString *)kid
{
    _commandName = [DataInterfaceUtil getDataInterface:@"cmd_json_get_resume_detail"];
    [self setAssetsFileInfo:_commandName suffix:nil];
    JsonCreater *creater = [[JsonCreater alloc] init];
    [creater setParam:@"devid" paramValue:[BaseJsonService getDevID]];
    [creater setParam:@"resumeid" paramValue:kid];
    [self getData:[creater createJson:_commandName] url:nil];
    
}
-(void)getTrainDetail:(NSString *)kid
{
    _commandName = [DataInterfaceUtil getDataInterface:@"cmd_json_get_train_detail"];
    [self setAssetsFileInfo:_commandName suffix:nil];
    JsonCreater *creater = [[JsonCreater alloc] init];
    [creater setParam:@"devid" paramValue:[BaseJsonService getDevID]];
    [creater setParam:@"trainid" paramValue:kid];
    [self getData:[creater createJson:_commandName] url:nil];
    
}
-(void)getProjectDetail:(NSString *)kid
{
    _commandName = [DataInterfaceUtil getDataInterface:@"cmd_json_get_project_detail"];
    [self setAssetsFileInfo:_commandName suffix:nil];
    JsonCreater *creater = [[JsonCreater alloc] init];
    [creater setParam:@"devid" paramValue:[BaseJsonService getDevID]];
    [creater setParam:@"projectid" paramValue:kid];
    [self getData:[creater createJson:_commandName] url:nil];
    
}
////////////////////////////////////////////////////////////////////////////////
#pragma mark - Private methods

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Actions

////////////////////////////////////////////////////////////////////////////////
#pragma mark - XXXDataSource / XXXDelegate methods


@end

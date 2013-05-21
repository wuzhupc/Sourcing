//
//  MobileInfoService.h
//  sourcing
//
//  Created by wuzhu on 13-1-1.
//  Copyright (c) 2013年 wuzhu. All rights reserved.
//

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Imports

#import "BaseJsonService.h"

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Types

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Defines & Constants

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Macros

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Interface

@interface MobileInfoService : BaseJsonService


////////////////////////////////////////////////////////////////////////////////
#pragma mark - Properties


////////////////////////////////////////////////////////////////////////////////
#pragma mark - Class Methods

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Instance Methods
-(void)getNewsList:(NSString *)knewstype bottomid:(NSString *)kid;
-(void)getNewsDetail:(NSString *)knewstype newsid:(NSString *)kid;
-(void)getJobList:(NSString *)ksearchkey bottomid:(NSString *)kid;
-(void)getResumeList:(NSString *)ksearchkey bottomid:(NSString *)kid;
-(void)getTrainList:(NSString *)ksearchkey bottomid:(NSString *)kid;
-(void)getProjectList:(NSString *)ksearchkey bottomid:(NSString *)kid;
-(void)getJobDetail:(NSString *)kid;
-(void)getResumeDetail:(NSString *)kid;
-(void)getTrainDetail:(NSString *)kid;
-(void)getProjectDetail:(NSString *)kid;
-(void)getCompanyDetail:(NSString *)kid;
-(void)getCompanyList:(NSString *)ksearchkey bottomid:(NSString *)kid;
@end

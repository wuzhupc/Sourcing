//
//  MobileUserService.h
//  sourcing
//
//  Created by wuzhu on 12-12-31.
//  Copyright (c) 2012年 wuzhu. All rights reserved.
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

@interface MobileUserService : BaseJsonService


////////////////////////////////////////////////////////////////////////////////
#pragma mark - Properties


////////////////////////////////////////////////////////////////////////////////
#pragma mark - Class Methods

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Instance Methods
-(void)userLogin:(NSString *)kusername password:(NSString *)kpwd;
-(void)userLogin:(NSString *)kusername password:(NSString *)kpwd  process:(BOOL)kshowprocess processcontent:(NSString *)kshowprocesscontent;
-(void)changePwd:(NSString *)kusername password:(NSString *)kpwd newpwd:(NSString *)knewpwd process:(BOOL)kshowprocess processcontent:(NSString *)kshowprocesscontent;
-(void)getUserConsultInfo:(NSString *)kbottomid process:(BOOL)kshowprocess processcontent:(NSString *)kshowprocesscontent;
-(void)sendUserConsult:(NSString *)kcontent process:(BOOL)kshowprocess processcontent:(NSString *)kshowprocesscontent;
-(void)getUserAuditInfo:(NSString *)kbottomid process:(BOOL)kshowprocess processcontent:(NSString *)kshowprocesscontent;
-(void)getUserDeclareInfo:(NSString *)kbottomid process:(BOOL)kshowprocess processcontent:(NSString *)kshowprocesscontent;
-(void)getUserNotifierInfo:(NSString *)kbottomid process:(BOOL)kshowprocess processcontent:(NSString *)kshowprocesscontent;


@end

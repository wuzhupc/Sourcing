//
//  SettingUtil.h
//  sourcing
//
//  Created by wuzhu on 13-1-31.
//  Copyright (c) 2013年 wuzhu. All rights reserved.
//

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Imports

#import <Foundation/Foundation.h>

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Types

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Defines & Constants
#define CSTR_SETTING_FONT @"fontsetting"
#define CSTR_SETTING_PUSH @"pushsetting"

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Macros

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Interface

@interface SettingUtil : NSObject


////////////////////////////////////////////////////////////////////////////////
#pragma mark - Properties


////////////////////////////////////////////////////////////////////////////////
#pragma mark - Class Methods
+(NSArray *)getFontDesc;
+(NSString *)getFontDescWithIndex:(NSUInteger)kindex;
+(NSUInteger)getFontSetting;
+(void)setFontSetting:(NSUInteger)kindex;
+(void)setPushSetting:(BOOL)kpush;
+(BOOL)getPushSetting;
////////////////////////////////////////////////////////////////////////////////
#pragma mark - Instance Methods


@end

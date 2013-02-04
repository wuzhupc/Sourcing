//
//  SettingUtil.m
//  sourcing
//
//  Created by wuzhu on 13-1-31.
//  Copyright (c) 2013年 wuzhu. All rights reserved.
//

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Imports

#import "SettingUtil.h"

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Types

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Defines & Constants

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Macros

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Private Interface
@interface SettingUtil ()

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Private Properties

@end

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Implementation

@implementation SettingUtil

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Synthesize

/* Public *********************************************************************/

/* Private ********************************************************************/

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Setup & Teardown

- (id)init
{
    self = [super init];
    if (self)
    {
        
    }
    return self;
}

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Superclass Overrides

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Public methods
+(NSArray *)getFontDesc
{
    NSArray *result = [[NSArray alloc] initWithObjects:@"小号字",@"中号字",@"大号字",@"特大号字", nil];
    return result;
}

+(NSString *)getFontDescWithIndex:(NSUInteger)kindex
{
    NSArray *fontdesc = [SettingUtil getFontDesc];
    if(kindex>[fontdesc count])
        return [fontdesc objectAtIndex:[fontdesc count]-1];
    return [fontdesc objectAtIndex:kindex];
}
+(NSUInteger)getFontSetting
{
    NSUserDefaults *setting = [NSUserDefaults standardUserDefaults];
    if([setting objectForKey:CSTR_SETTING_FONT]==nil)
        return 1;//默认为中字号
    return [setting integerForKey:CSTR_SETTING_FONT];
}
+(void)setFontSetting:(NSUInteger)kindex
{
    NSUserDefaults *setting = [NSUserDefaults standardUserDefaults];
    [setting setInteger:kindex forKey:CSTR_SETTING_FONT];
}

+(BOOL)getPushSetting
{
    NSUserDefaults *setting = [NSUserDefaults standardUserDefaults];
    if([setting objectForKey:CSTR_SETTING_PUSH]==nil)
        return YES;
    return [setting boolForKey:CSTR_SETTING_PUSH];
}

+(void)setPushSetting:(BOOL)kpush
{
    BOOL last = [SettingUtil getPushSetting];
    if(kpush==last)
        return;
    NSUserDefaults *setting = [NSUserDefaults standardUserDefaults];
    [setting setBool:kpush forKey:CSTR_SETTING_PUSH];
    //TODO 将设置发回服务端
}

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Private methods

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Actions

////////////////////////////////////////////////////////////////////////////////
#pragma mark - XXXDataSource / XXXDelegate methods


@end

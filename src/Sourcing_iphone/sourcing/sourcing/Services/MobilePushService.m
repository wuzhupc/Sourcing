//
//  MobilePushService.m
//  sourcing
//
//  Created by wuzhu_mac on 13-5-21.
//  Copyright (c) 2013年 wuzhu. All rights reserved.
//

#import "MobilePushService.h"
#import "DataInterfaceUtil.h"
#import "JsonCreater.h"
#import "UserVO.h"
#import "ApplicationSet.h"
#import "StringUtil.h"

@implementation MobilePushService

-(void)sendDeviceToken:(NSInteger)uid devicetoken:(NSString *)kDeviceToken;
{
    _commandName = [DataInterfaceUtil getDataInterface:@"cmd_json_send_devicetoken"];
    [self setAssetsFileInfo:_commandName suffix:nil];
    JsonCreater *creater = [[JsonCreater alloc] init];
    [creater setParam:@"devid" paramValue:[BaseJsonService getDevID]];
    [creater setParamNSInteger:@"userid" paramValue:uid];
    [creater setParam:@"devicetoken" paramValue:kDeviceToken];
    [self getData:[creater createJson:_commandName] url:nil];
}
@end

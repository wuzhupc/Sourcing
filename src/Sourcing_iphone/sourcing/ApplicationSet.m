//
//  ApplicationSet.m
//  sourcing
//
//  Created by wuzhu on 12-12-25.
//  Copyright (c) 2012年 wuzhu. All rights reserved.
//

#import "ApplicationSet.h"
#import "StringUtil.h"
#import "MobilePushService.h"
#import "SettingUtil.h"

@interface ApplicationSet()
{
@private
    //用户信息
    UserVO *_userVO;
    //设备号
    NSString *_deviceToken;
}
@end


@implementation ApplicationSet
@synthesize channels = channels_;

static ApplicationSet *_shareData = nil;

+(ApplicationSet *)shareData
{
    @synchronized([ApplicationSet class])
    {
        if(!_shareData)
        {
            _shareData = [[ApplicationSet alloc] init];
        }
        return _shareData;
    }
}
-(id)init
{
    if(_shareData)
        return  _shareData;
    self = [super init];
    return  self;
}

-(void)setPushSetting:(BOOL)allowpush
{
    if(_userVO==nil||[StringUtil isEmptyStr:_deviceToken])
        return;
    MobilePushService *service = [[MobilePushService alloc] initWithDelegate:nil tag:0];
    [service sendDeviceToken:_userVO.Userid devicetoken:(allowpush?_deviceToken:@"")];
}

-(void)setDeviceToken:(NSString *)deviceToken
{
    if(_userVO!=nil)
    {
        MobilePushService *service = [[MobilePushService alloc] initWithDelegate:nil tag:0];
        [service sendDeviceToken:_userVO.Userid devicetoken:deviceToken];
    }
    _deviceToken = deviceToken;
}

-(void)setLoginUserInfo:(UserVO *)kuserVO saveinfo:(BOOL)ksave
{
    //是否在这里重新判断要不要发送deviceToken
    if((_userVO==nil&&kuserVO!=nil)||(_userVO!=nil&&kuserVO==nil)||
       (_userVO!=nil&&kuserVO!=nil&&_userVO.Userid!=kuserVO.Userid))
    {
        MobilePushService *service = [[MobilePushService alloc] initWithDelegate:nil tag:0];
        if(![StringUtil isEmptyStr:_deviceToken])
        {
            if(_userVO!=nil)
            {
                //清除旧的devicetoken
                [service sendDeviceToken:_userVO.Userid devicetoken:@""];
            }
            if(kuserVO!=nil&&[SettingUtil getPushSetting])
            {
                //设置新的设备
                [service sendDeviceToken:kuserVO.Userid devicetoken:_deviceToken];
            }
        }
    }
    _userVO = kuserVO;
    if (ksave) {
        [UserVO saveLoginUserInfo:kuserVO];
    }
}

-(UserVO *)getUserVO
{
    return _userVO;
}

@end

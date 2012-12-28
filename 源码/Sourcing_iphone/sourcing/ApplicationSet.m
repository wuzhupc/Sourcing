//
//  ApplicationSet.m
//  sourcing
//
//  Created by wuzhu on 12-12-25.
//  Copyright (c) 2012年 wuzhu. All rights reserved.
//

#import "ApplicationSet.h"

@implementation ApplicationSet

@synthesize isRegDevToken=_isRegDevToken;
@synthesize deviceToken = _deviceToken;
@synthesize channels = _channels;

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

-(void)setLoginUserInfo:(UserVO *)kuserVO saveinfo:(BOOL)ksave
{
    //TODO 是否在这里重新判断要不要发送deviceToken
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

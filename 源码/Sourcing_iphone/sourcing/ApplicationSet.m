//
//  ApplicationSet.m
//  sourcing
//
//  Created by wuzhu on 12-12-25.
//  Copyright (c) 2012年 wuzhu. All rights reserved.
//

#import "ApplicationSet.h"


@interface ApplicationSet()
{
@private
    //用户信息
    UserVO *_userVO;
}
@end


@implementation ApplicationSet

@synthesize isRegDevToken=isRegDevToken_;
@synthesize deviceToken = deviceToken_;
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

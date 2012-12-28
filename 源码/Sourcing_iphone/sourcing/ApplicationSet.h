//
//  ApplicationSet.h
//  sourcing
//
//  Created by wuzhu on 12-12-25.
//  Copyright (c) 2012年 wuzhu. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "UserVO.h"

@interface ApplicationSet : NSObject
{
    //是否已经注册过设备号
    BOOL _isRegDevToken;
    //设备号
    NSString *_deviceToken;
    //所有的栏目列表
    NSArray *_channels;
    //用户信息
    UserVO *_userVO;
}

@property BOOL isRegDevToken;
@property (nonatomic) NSString *deviceToken;
@property NSArray *channels;

+(ApplicationSet *)shareData;

-(void)setLoginUserInfo:(UserVO *)kuserVO saveinfo:(BOOL)ksave;
-(UserVO *)getUserVO;
@end

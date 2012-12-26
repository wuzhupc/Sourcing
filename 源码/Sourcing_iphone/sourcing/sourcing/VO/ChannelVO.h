//
//  ChannelVO.h
//  sourcing
//
//  Created by wuzhu on 12-12-26.
//  Copyright (c) 2012年 wuzhu. All rights reserved.
//

#import "BaseVO.h"

@interface ChannelVO : BaseVO
{
    NSString *channelID;
    NSString *channelName;
    NSString *fatherchannelID;
    NSString *isdefault;
    NSString *sort;
    NSString *type;
    BOOL isFirstLoad;
    NSString *lastUpdateDataTime;
    NSString *mustusertypes;
}

@property NSString *channelName;
@property NSString *fatherchannelID;
@property NSString *isdefault;
@property NSString *sort;
@property NSString *type;
@property BOOL isFirstLoad;
@property NSString *lastUpdateDataTime;
@property NSString *mustusertypes;
-(void)setChannelID:(NSString *)kid;
-(NSString *)ChannelID;
@end

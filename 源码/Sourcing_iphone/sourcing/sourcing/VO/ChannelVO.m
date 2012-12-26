//
//  ChannelVO.m
//  sourcing
//
//  Created by wuzhu on 12-12-26.
//  Copyright (c) 2012年 wuzhu. All rights reserved.
//

#import "ChannelVO.h"

@implementation ChannelVO
@synthesize channelName;
@synthesize fatherchannelID;
@synthesize isdefault;
@synthesize sort;
@synthesize type;
@synthesize isFirstLoad;
@synthesize lastUpdateDataTime;
@synthesize mustusertypes;
-(void)setChannelID:(NSString *)kid
{
    _id = kid;
    channelID = kid;
}
-(NSString *)ChannelID
{
    return channelID;
}

-(id)init
{
    self =[super init];
    if(self)
    {
        self.isFirstLoad = YES;
    }
    return self;
}


@end

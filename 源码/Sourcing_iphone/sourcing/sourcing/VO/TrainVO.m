//
//  TrainVO.m
//  sourcing
//
//  Created by wuzhu on 12-12-26.
//  Copyright (c) 2012年 wuzhu. All rights reserved.
//

#import "TrainVO.h"

@implementation TrainVO
@synthesize trainname = trainname;
-(void)setTrainid:(NSInteger )kid
{
    _id = kid;
    trainid = kid;
}
-(NSInteger )Trainid
{
    return trainid;
}

@end

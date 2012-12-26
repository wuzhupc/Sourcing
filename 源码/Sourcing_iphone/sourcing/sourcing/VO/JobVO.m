//
//  JobVO.m
//  sourcing
//
//  Created by wuzhu on 12-12-26.
//  Copyright (c) 2012年 wuzhu. All rights reserved.
//

#import "JobVO.h"

@implementation JobVO
@synthesize job = job;
@synthesize company = company;
@synthesize publishtime = publishtime;

-(void)setJobid:(NSString *)kid
{
    _id =kid;
    jobid = kid;
}
-(NSString *)Jobid
{
    return jobid;
}

@end

//
//  JobVO.m
//  sourcing
//
//  Created by wuzhu on 12-12-26.
//  Copyright (c) 2012年 wuzhu. All rights reserved.
//

#import "JobVO.h"

@interface JobVO()
{
@private
    NSInteger jobid;
}
@end

@implementation JobVO
@synthesize job = job_;
@synthesize company = company_;
@synthesize publishtime = publishtime_;

-(void)setJobid:(NSInteger )kid
{
    self.Id =kid;
    jobid = kid;
}
-(NSInteger )Jobid
{
    return jobid;
}

#pragma mark - Superclass Overrides

-(CGFloat)heightForCell:(NSInteger)kindex
{
    return 60.0f;
}

#pragma mark - BaseServiceDelegate
-(void)serviceResult:(ResponseVO *)result
{
    
}

@end

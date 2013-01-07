//
//  JobVO.h
//  sourcing
//
//  Created by wuzhu on 12-12-26.
//  Copyright (c) 2012年 wuzhu. All rights reserved.
//

#import "BaseVO.h"

@interface JobVO : BaseVO
{
    NSInteger jobid;
    NSString *job;
    NSString *company;
    NSString *publishtime;
}

@property NSString *job;
@property NSString *company;
@property NSString *publishtime;
-(void)setJobid:(NSInteger )kid;
-(NSInteger )Jobid;
@end

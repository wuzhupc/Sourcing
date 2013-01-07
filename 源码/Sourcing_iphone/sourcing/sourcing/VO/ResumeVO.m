//
//  ResumeVO.m
//  sourcing
//
//  Created by wuzhu on 12-12-26.
//  Copyright (c) 2012年 wuzhu. All rights reserved.
//

#import "ResumeVO.h"

@implementation ResumeVO

@synthesize  expectjob = expectjob;
@synthesize name = name;
@synthesize  publishtime = publishtime;
@synthesize  resumetitle = resumetitle;

-(void)setResumeid:(NSInteger )kid
{
    _id = kid;
    resumeid = kid;
}
-(NSInteger )Resumeid
{
    return resumeid;
}

@end

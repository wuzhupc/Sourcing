//
//  ResumeVO.m
//  sourcing
//
//  Created by wuzhu on 12-12-26.
//  Copyright (c) 2012年 wuzhu. All rights reserved.
//

#import "ResumeVO.h"

@interface ResumeVO ()
{
@private
    NSInteger resumeid;
}

@end

@implementation ResumeVO

@synthesize  expectjob = expectjob_;
@synthesize name = name_;
@synthesize  publishtime = publishtime_;
@synthesize  resumetitle = resumetitle_;

-(void)setResumeid:(NSInteger )kid
{
    self.Id = kid;
    resumeid = kid;
}
-(NSInteger )Resumeid
{
    return resumeid;
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

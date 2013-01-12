//
//  ProjectVO.m
//  sourcing
//
//  Created by wuzhu on 12-12-26.
//  Copyright (c) 2012年 wuzhu. All rights reserved.
//

#import "ProjectVO.h"

@interface ProjectVO ()
{
@private
    NSInteger projectid;
}
@end

@implementation ProjectVO

@synthesize projectname = projectname;
@synthesize publishtime = publishtime;
@synthesize projectstatus = projectstatus;

-(void)setProjectid:(NSInteger )kid
{
    self.Id = kid;
    projectid = kid;
}
-(NSInteger )Projectid
{
    return projectid;
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

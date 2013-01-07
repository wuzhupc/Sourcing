//
//  ProjectVO.m
//  sourcing
//
//  Created by wuzhu on 12-12-26.
//  Copyright (c) 2012年 wuzhu. All rights reserved.
//

#import "ProjectVO.h"

@implementation ProjectVO

@synthesize projectname = projectname;
@synthesize publishtime = publishtime;
@synthesize projectstatus = projectstatus;

-(void)setProjectid:(NSInteger )kid
{
    _id = kid;
    projectid = kid;
}
-(NSInteger )Projectid
{
    return projectid;
}
@end

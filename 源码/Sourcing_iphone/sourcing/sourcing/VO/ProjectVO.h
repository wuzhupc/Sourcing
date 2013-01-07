//
//  ProjectVO.h
//  sourcing
//
//  Created by wuzhu on 12-12-26.
//  Copyright (c) 2012年 wuzhu. All rights reserved.
//

#import "BaseVO.h"

@interface ProjectVO : BaseVO
{
    NSInteger projectid;
    NSString *projectname;
    NSString *projectstatus;
    NSString *publishtime;
}

@property NSString *projectname;
@property NSString *projectstatus;
@property NSString *publishtime;

-(void)setProjectid:(NSInteger )kid;
-(NSInteger )Projectid;

@end

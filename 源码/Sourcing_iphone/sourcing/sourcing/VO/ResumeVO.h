//
//  ResumeVO.h
//  sourcing
//
//  Created by wuzhu on 12-12-26.
//  Copyright (c) 2012年 wuzhu. All rights reserved.
//

#import "BaseVO.h"

@interface ResumeVO : BaseVO
{
    NSString *resumeid;
    NSString *expectjob;
    NSString *name;
    NSString *publishtime;
    NSString *resumetitle;
}

@property NSString *expectjob;
@property NSString *name;
@property NSString *publishtime;
@property NSString *resumetitle;

-(void)setResumeid:(NSString *)kid;
-(NSString *)Resumeid;

@end

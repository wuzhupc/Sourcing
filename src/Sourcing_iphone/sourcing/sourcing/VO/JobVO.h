//
//  JobVO.h
//  sourcing
//
//  Created by wuzhu on 12-12-26.
//  Copyright (c) 2012年 wuzhu. All rights reserved.
//

#import "BaseVO.h"
#import "BaseServiceDelegate.h"


#define CINT_TAG_GETJOBDETAIL 10007

@interface JobVO : BaseVO<BaseServiceDelegate>

@property (nonatomic,strong)NSString *job;
@property (nonatomic,strong)NSString *company;
@property (nonatomic,strong)NSString *publishtime;

-(void)setJobid:(NSInteger )kid;
-(NSInteger )Jobid;
@end

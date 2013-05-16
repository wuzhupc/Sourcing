//
//  ResumeVO.h
//  sourcing
//
//  Created by wuzhu on 12-12-26.
//  Copyright (c) 2012年 wuzhu. All rights reserved.
//

#import "BaseVO.h"
#import "BaseServiceDelegate.h"

#define CINT_TAG_GETRESUMEDETAIL 10009

@interface ResumeVO : BaseVO<BaseServiceDelegate>

@property (nonatomic,strong)NSString *expectjob;
@property (nonatomic,strong)NSString *name;
@property (nonatomic,strong)NSString *publishtime;
@property (nonatomic,strong)NSString *resumetitle;

-(void)setResumeid:(NSInteger )kid;
-(NSInteger )Resumeid;

@end

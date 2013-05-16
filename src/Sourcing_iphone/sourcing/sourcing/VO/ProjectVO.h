//
//  ProjectVO.h
//  sourcing
//
//  Created by wuzhu on 12-12-26.
//  Copyright (c) 2012年 wuzhu. All rights reserved.
//

#import "BaseVO.h"
#import "BaseServiceDelegate.h"

#define CINT_TAG_GETPROJECTDETAIL 10008

@interface ProjectVO : BaseVO<BaseServiceDelegate>

@property (nonatomic,strong)NSString *projectname;
@property (nonatomic,strong)NSString *projectstatus;
@property (nonatomic,strong)NSString *publishtime;

-(void)setProjectid:(NSInteger )kid;
-(NSInteger )Projectid;

@end

//
//  AuditVO.h
//  sourcing
//
//  Created by wuzhu on 12-12-26.
//  Copyright (c) 2012年 wuzhu. All rights reserved.
//

#import "BaseVO.h"
#import "AuditResultVO.h"

@interface AuditVO : BaseVO

@property (nonatomic,strong)NSString *auditcontent;
@property (nonatomic,strong)NSString *publishtime;
@property (nonatomic,strong)AuditResultVO *auditResultVO;

-(void)setAuditid:(NSInteger)kauditid;
-(NSInteger)Auditid;

@end

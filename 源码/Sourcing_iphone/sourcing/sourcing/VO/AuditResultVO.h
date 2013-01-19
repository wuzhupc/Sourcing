//
//  AuditResultVO.h
//  sourcing
//
//  Created by wuzhu on 12-12-26.
//  Copyright (c) 2012年 wuzhu. All rights reserved.
//

#import "BaseVO.h"

@interface AuditResultVO : BaseVO

@property (nonatomic,strong)NSString *auditresultcontent;
@property (nonatomic,strong)NSString *publisher;
@property (nonatomic,strong)NSString *publishtime;
@property (nonatomic,strong)NSString *auditresult;

-(void)setAuditresultid:(NSInteger)kresultid;
-(NSInteger)Auditresultid;
-(NSString *)getAuditStatus;
@end

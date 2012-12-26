//
//  AuditResultVO.h
//  sourcing
//
//  Created by wuzhu on 12-12-26.
//  Copyright (c) 2012年 wuzhu. All rights reserved.
//

#import "BaseVO.h"

@interface AuditResultVO : BaseVO
{
    NSString *auditresultid;
    NSString *auditresultcontent;
    NSString *publisher;
    NSString *publishtime;
    NSString *auditresult;
}

@property NSString *auditresultcontent;
@property NSString *publisher;
@property NSString *publishtime;
@property NSString *auditresult;

-(void)setAuditresultid:(NSString *)kresultid;
-(NSString *)Auditresultid;

@end

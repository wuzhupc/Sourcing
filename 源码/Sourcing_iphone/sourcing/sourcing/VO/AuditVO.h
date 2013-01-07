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
{
    NSInteger auditid;
    NSString *auditcontent;
    NSString *publishtime;
    AuditResultVO *auditResultVO;
}
@property NSString *auditcontent;
@property NSString *publishtime;
@property AuditResultVO *auditResultVO;

-(void)setAuditid:(NSInteger)kauditid;
-(NSInteger)Auditid;

@end

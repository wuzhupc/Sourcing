//
//  AuditVO.m
//  sourcing
//
//  Created by wuzhu on 12-12-26.
//  Copyright (c) 2012年 wuzhu. All rights reserved.
//

#import "AuditVO.h"

@implementation AuditVO

@synthesize auditcontent = auditcontent;
@synthesize auditResultVO = auditResultVO;
@synthesize publishtime = publishtime;

-(void)setAuditid:(NSInteger)kauditid
{
    auditid = kauditid;
    _id = kauditid;
}
-(NSInteger)Auditid
{
    return auditid;
}

@end

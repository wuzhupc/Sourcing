//
//  AuditResultVO.m
//  sourcing
//
//  Created by wuzhu on 12-12-26.
//  Copyright (c) 2012年 wuzhu. All rights reserved.
//

#import "AuditResultVO.h"

@implementation AuditResultVO

@synthesize auditresult = auditresult;
@synthesize auditresultcontent = auditresultcontent;
@synthesize publisher = publisher;
@synthesize publishtime = publishtime;

-(void)setAuditresultid:(NSString *)kresultid
{
    _id = kresultid;
    auditresultid = kresultid;
}
-(NSString *)Auditresultid
{
    return auditresultid;
}

@end

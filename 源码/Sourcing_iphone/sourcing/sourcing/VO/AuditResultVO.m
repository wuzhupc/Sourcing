//
//  AuditResultVO.m
//  sourcing
//
//  Created by wuzhu on 12-12-26.
//  Copyright (c) 2012年 wuzhu. All rights reserved.
//

#import "AuditResultVO.h"

@interface AuditResultVO()
{
@private
    NSInteger auditresultid;
}

@end

@implementation AuditResultVO

@synthesize auditresult = auditresult_;
@synthesize auditresultcontent = auditresultcontent_;
@synthesize publisher = publisher_;
@synthesize publishtime = publishtime_;

-(void)setAuditresultid:(NSInteger)kresultid
{
    self.Id = kresultid;
    auditresultid = kresultid;
}
-(NSInteger)Auditresultid
{
    return auditresultid;
}

@end

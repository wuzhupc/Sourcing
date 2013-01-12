//
//  AuditVO.m
//  sourcing
//
//  Created by wuzhu on 12-12-26.
//  Copyright (c) 2012年 wuzhu. All rights reserved.
//

#import "AuditVO.h"

@interface AuditVO()
{
@private
    NSInteger auditid;
}
@end

@implementation AuditVO

@synthesize auditcontent = auditcontent_;
@synthesize auditResultVO = auditResultVO_;
@synthesize publishtime = publishtime_;

-(void)setAuditid:(NSInteger)kauditid
{
    auditid = kauditid;
    self.Id = kauditid;
}
-(NSInteger)Auditid
{
    return auditid;
}

@end

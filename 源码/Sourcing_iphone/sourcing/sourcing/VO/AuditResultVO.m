//
//  AuditResultVO.m
//  sourcing
//
//  Created by wuzhu on 12-12-26.
//  Copyright (c) 2012年 wuzhu. All rights reserved.
//

#import "AuditResultVO.h"
#import "StringUtil.h"

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

-(NSString *)getAuditStatus
{
    if([StringUtil strToBOOL:self.auditresult])
        return @"审核通过";
    else
        return @"审核未通过";
}

@end

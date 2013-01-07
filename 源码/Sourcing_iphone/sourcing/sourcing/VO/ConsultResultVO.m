//
//  ConsultResultVO.m
//  sourcing
//
//  Created by wuzhu on 12-12-26.
//  Copyright (c) 2012年 wuzhu. All rights reserved.
//

#import "ConsultResultVO.h"

@implementation ConsultResultVO

@synthesize consultresultcontent = consultresultcontent;
@synthesize publishtime = publishtime;
@synthesize publisher = publisher;

-(void)setConsultresultid:(NSInteger)kresultid
{
    consultresultid = kresultid;
    _id = kresultid;
}
-(NSInteger)Consultresultid
{
    return consultresultid;
}
@end

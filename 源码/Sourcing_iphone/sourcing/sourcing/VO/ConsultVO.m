//
//  ConsultVO.m
//  sourcing
//
//  Created by wuzhu on 12-12-26.
//  Copyright (c) 2012年 wuzhu. All rights reserved.
//

#import "ConsultVO.h"

@implementation ConsultVO

@synthesize consultcontent = consultcontent;
@synthesize publishtime = publishtime;
@synthesize consultResultVO = consultResultVO;

-(void)setConsultid:(NSString *)kconsultid
{
    consultid = kconsultid;
    _id = kconsultid;
}
-(NSString *)Consultid
{
    return consultid;
}

@end

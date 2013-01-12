//
//  ConsultResultVO.m
//  sourcing
//
//  Created by wuzhu on 12-12-26.
//  Copyright (c) 2012年 wuzhu. All rights reserved.
//

#import "ConsultResultVO.h"

@interface ConsultResultVO()
{
@private
    NSInteger consultresultid;
}
@end

@implementation ConsultResultVO

@synthesize consultresultcontent = consultresultcontent_;
@synthesize publishtime = publishtime_;
@synthesize publisher = publisher_;

-(void)setConsultresultid:(NSInteger)kresultid
{
    consultresultid = kresultid;
    self.Id = kresultid;
}
-(NSInteger)Consultresultid
{
    return consultresultid;
}
@end

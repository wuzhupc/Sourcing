//
//  ConsultVO.m
//  sourcing
//
//  Created by wuzhu on 12-12-26.
//  Copyright (c) 2012年 wuzhu. All rights reserved.
//

#import "ConsultVO.h"

@interface ConsultVO()
{
@private
    NSInteger consultid;
}
@end

@implementation ConsultVO

@synthesize consultcontent = consultcontent_;
@synthesize publishtime = publishtime_;
@synthesize consultResultVO = consultResultVO_;

-(void)setConsultid:(NSInteger )kconsultid
{
    consultid = kconsultid;
    self.Id = kconsultid;
}
-(NSInteger )Consultid
{
    return consultid;
}

@end

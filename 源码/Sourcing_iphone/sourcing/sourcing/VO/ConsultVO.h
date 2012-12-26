//
//  ConsultVO.h
//  sourcing
//
//  Created by wuzhu on 12-12-26.
//  Copyright (c) 2012年 wuzhu. All rights reserved.
//

#import "BaseVO.h"
#import "ConsultResultVO.h"

@interface ConsultVO : BaseVO
{
    NSString *consultid;
    NSString *publishtime;
    NSString *consultcontent;
    ConsultResultVO *consultResultVO;
}

@property NSString *publishtime;
@property NSString *consultcontent;
@property ConsultResultVO *consultResultVO;

-(void)setConsultid:(NSString *)kconsultid;
-(NSString *)Consultid;

@end

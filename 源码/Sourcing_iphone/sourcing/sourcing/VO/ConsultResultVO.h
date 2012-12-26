//
//  ConsultResultVO.h
//  sourcing
//
//  Created by wuzhu on 12-12-26.
//  Copyright (c) 2012年 wuzhu. All rights reserved.
//

#import "BaseVO.h"

@interface ConsultResultVO : BaseVO
{
    NSString *consultresultid;
    NSString *consultresultcontent;
    NSString *publisher;
    NSString *publishtime;
}

@property NSString *consultresultcontent;
@property NSString *publisher;
@property NSString *publishtime;

-(void)setConsultresultid:(NSString *)kresultid;
-(NSString *)Consultresultid;
@end

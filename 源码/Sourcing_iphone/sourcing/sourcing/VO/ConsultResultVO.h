//
//  ConsultResultVO.h
//  sourcing
//
//  Created by wuzhu on 12-12-26.
//  Copyright (c) 2012年 wuzhu. All rights reserved.
//

#import "BaseVO.h"

@interface ConsultResultVO : BaseVO

@property (nonatomic,strong)NSString *consultresultcontent;
@property (nonatomic,strong)NSString *publisher;
@property (nonatomic,strong)NSString *publishtime;

-(void)setConsultresultid:(NSInteger )kresultid;
-(NSInteger )Consultresultid;
@end

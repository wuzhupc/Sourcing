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

@property (nonatomic,strong)NSString *publishtime;
@property (nonatomic,strong)NSString *consultcontent;
@property (nonatomic,strong)ConsultResultVO *consultResultVO;

-(void)setConsultid:(NSInteger )kconsultid;
-(NSInteger )Consultid;

@end

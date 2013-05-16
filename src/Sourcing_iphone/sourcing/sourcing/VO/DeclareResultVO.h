//
//  DeclareResultVO.h
//  sourcing
//
//  Created by wuzhu on 12-12-26.
//  Copyright (c) 2012年 wuzhu. All rights reserved.
//

#import "BaseVO.h"

@interface DeclareResultVO : BaseVO

@property (nonatomic,strong)NSString *declareresult;
@property (nonatomic,strong)NSString *declareresultcontent;
@property (nonatomic,strong)NSString *publisher;
@property (nonatomic,strong)NSString *publishtime;

-(void)setDeclareresultid:(NSInteger )kresultid;
-(NSInteger )Declareresultid;
-(NSString *)getDeclareStatus;
@end

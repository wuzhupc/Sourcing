//
//  DeclareVO.h
//  sourcing
//
//  Created by wuzhu on 12-12-26.
//  Copyright (c) 2012年 wuzhu. All rights reserved.
//

#import "BaseVO.h"
#import "DeclareResultVO.h"

@interface DeclareVO : BaseVO

@property (nonatomic,strong)NSString *declarecontent;
@property (nonatomic,strong)NSString *publishtime;
@property (nonatomic,strong)DeclareResultVO *declareResultVO;
-(void)setDeclareid:(NSInteger )kdeclareid;
-(NSInteger )Declareid;
-(NSString *)getDeclareStatus;
@end

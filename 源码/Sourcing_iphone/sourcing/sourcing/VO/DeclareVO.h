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
{
    NSInteger declareid;
    NSString *declarecontent;
    NSString *publishtime;
    DeclareResultVO *declareResultVO;
}

@property NSString *declarecontent;
@property NSString *publishtime;
@property DeclareResultVO *declareResultVO;
-(void)setDeclareid:(NSInteger )kdeclareid;
-(NSInteger )Declareid;
@end

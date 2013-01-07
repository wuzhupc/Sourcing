//
//  DeclareResultVO.h
//  sourcing
//
//  Created by wuzhu on 12-12-26.
//  Copyright (c) 2012年 wuzhu. All rights reserved.
//

#import "BaseVO.h"

@interface DeclareResultVO : BaseVO
{
    NSInteger declareresultid;
    NSString *declareresult;
    NSString *declareresultcontent;
    NSString *publisher;
    NSString *publishtime;
}

@property NSString *declareresult;
@property NSString *declareresultcontent;
@property NSString *publisher;
@property NSString *publishtime;

-(void)setDeclareresultid:(NSInteger )kresultid;
-(NSInteger )Declareresultid;
@end

//
//  DeclareVO.m
//  sourcing
//
//  Created by wuzhu on 12-12-26.
//  Copyright (c) 2012年 wuzhu. All rights reserved.
//

#import "DeclareVO.h"

@implementation DeclareVO
@synthesize declarecontent = declarecontent;
@synthesize publishtime = publishtime;
@synthesize declareResultVO = declareResultVO;

-(void)setDeclareid:(NSInteger)kdeclareid
{
    declareid = kdeclareid;
    _id = kdeclareid;
}
-(NSInteger )Declareid
{
    return declareid;
}
@end

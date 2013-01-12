//
//  DeclareVO.m
//  sourcing
//
//  Created by wuzhu on 12-12-26.
//  Copyright (c) 2012年 wuzhu. All rights reserved.
//

#import "DeclareVO.h"

@interface DeclareVO()
{
@private
    NSInteger declareid;
}
@end

@implementation DeclareVO

@synthesize declarecontent = declarecontent_;
@synthesize publishtime = publishtime_;
@synthesize declareResultVO = declareResultVO_;

-(void)setDeclareid:(NSInteger)kdeclareid
{
    declareid = kdeclareid;
    self.Id = kdeclareid;
}
-(NSInteger )Declareid
{
    return declareid;
}
@end

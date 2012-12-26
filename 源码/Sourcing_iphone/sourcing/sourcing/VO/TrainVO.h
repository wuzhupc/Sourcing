//
//  TrainVO.h
//  sourcing
//
//  Created by wuzhu on 12-12-26.
//  Copyright (c) 2012年 wuzhu. All rights reserved.
//

#import "BaseVO.h"

@interface TrainVO : BaseVO
{
    NSString *trainid;
    NSString *trainname;
}
@property NSString *trainname;
-(void)setTrainid:(NSString *)kid;
-(NSString *)Trainid;
@end

//
//  TrainVO.h
//  sourcing
//
//  Created by wuzhu on 12-12-26.
//  Copyright (c) 2012年 wuzhu. All rights reserved.
//

#import "BaseVO.h"
#import "BaseServiceDelegate.h"

#define CINT_TAG_GETTRAINDETAIL 10010

@interface TrainVO : BaseVO<BaseServiceDelegate>

@property (nonatomic,strong)NSString *trainname;
-(void)setTrainid:(NSInteger )kid;
-(NSInteger )Trainid;
@end
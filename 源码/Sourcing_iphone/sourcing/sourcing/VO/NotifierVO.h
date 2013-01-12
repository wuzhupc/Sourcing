//
//  NotifierVO.h
//  sourcing
//
//  Created by wuzhu on 12-12-26.
//  Copyright (c) 2012年 wuzhu. All rights reserved.
//

#import "BaseVO.h"

@interface NotifierVO : BaseVO


@property (nonatomic,strong)NSString *notifiercontent;
@property (nonatomic,strong)NSString *publisher;
@property (nonatomic,strong)NSString *publishtime;

-(void)setNotifierid:(NSInteger )kid;
-(NSInteger )Notifierid;

@end

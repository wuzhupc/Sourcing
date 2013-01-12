//
//  TrainVO.m
//  sourcing
//
//  Created by wuzhu on 12-12-26.
//  Copyright (c) 2012年 wuzhu. All rights reserved.
//

#import "TrainVO.h"
#import "TrainCell.h"

@interface TrainVO ()
{
@private
    NSInteger trainid;
}
@end

@implementation TrainVO

@synthesize trainname = trainname_;
-(void)setTrainid:(NSInteger )kid
{
    self.Id = kid;
    trainid = kid;
}
-(NSInteger )Trainid
{
    return trainid;
}

#pragma mark - Superclass Overrides

-(CGFloat)heightForCell:(NSInteger)kindex
{
    return 44.0f;
}

-(UITableViewCell *)tableView:(UITableView *)tableView index:(NSInteger)kindex
{
    
}

#pragma mark - BaseServiceDelegate
-(void)serviceResult:(ResponseVO *)result
{
    
}

@end

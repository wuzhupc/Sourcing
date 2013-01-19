//
//  DeclareVO.m
//  sourcing
//
//  Created by wuzhu on 12-12-26.
//  Copyright (c) 2012年 wuzhu. All rights reserved.
//

#import "DeclareVO.h"
#import "ConsultCell.h"

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

-(CGFloat)heightForCell:(NSInteger)kindex
{
    return [ConsultCell calCellHeight:self];
}

-(UITableViewCell *)tableView:(UITableView *)tableView index:(NSInteger)kindex
{
    static NSString *cellIdentifier = @"ConsultCell";
    ConsultCell *cell = [tableView dequeueReusableCellWithIdentifier:cellIdentifier];
    if(cell==nil)
    {
        NSArray *nib = [[NSBundle mainBundle]loadNibNamed:cellIdentifier owner:self options:nil];
        cell = [nib objectAtIndex:0];
    }
    [cell setData:self];
    return cell;
}
-(NSString *)getDeclareStatus
{
    if(self.declareResultVO == nil)
        return @"等待处理中";
    return [self.declareResultVO getDeclareStatus];
}
@end
